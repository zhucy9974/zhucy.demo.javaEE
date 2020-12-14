package demo.tool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class DBTools {

	private DBTools() {
		throw new IllegalStateException("Utility class");
	}

	public static <E> Map<String, Object> getElementsByCriteria(Map<String, String> criterias, Class<E> c,
			EntityManager entityManager) {
		return getElementsByCriteria(criterias, c, entityManager, -1, -1);
	}

	public static <E> Map<String, Object> getElementsByCriteria(Map<String, String> criterias, Class<E> c,
			EntityManager entityManager, int pageNumber, int pageSize) {
		String orderBy = criterias.get("orderBy");
		criterias.remove("orderBy");
		boolean hasCriterias = !CollectionUtils.isEmpty(criterias);
		StringBuilder jpql = new StringBuilder().append("select @e from " + c.getName() + " e ");
		if (hasCriterias) {
			jpql.append(" where ");
			Iterables.forEach(criterias, (i, entry) -> jpql.append(" e." + entry.getKey() + " like concat('%',:"
					+ entry.getKey() + ",'%')" + ((i == (criterias.size() - 1)) ? "" : " and ")));
		}
		
		if(StringUtils.isNotBlank(orderBy)) {
			jpql.append(" order by e."+orderBy);
		}

		TypedQuery<Long> queryCount = entityManager.createQuery(jpql.toString().replace("@e", "count(e)"), Long.class);
		if (hasCriterias)
			Iterables.forEach(criterias, (i, entry) -> queryCount.setParameter(entry.getKey(), entry.getValue()));
		Long totelEl = queryCount.getSingleResult();

		TypedQuery<E> query = entityManager.createQuery(jpql.toString().replace("@e", "e"), c);
		if (hasCriterias)
			Iterables.forEach(criterias, (i, entry) -> query.setParameter(entry.getKey(), entry.getValue()));

		if (pageNumber != -1 && pageSize != -1) {
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
		}

		Map<String, Object> res = new HashMap<>();
		res.put("maxResults", totelEl);
		res.put("results", query.getResultList());
		return res;

	}
}
