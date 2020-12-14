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
		
		//récupérer l'info order by, et puis supprimer cette info pour que le 
		//map est pure pour les critaires
		String orderBy = null;
		if(criterias!=null) {
			orderBy = criterias.get("orderBy");
			criterias.remove("orderBy");
		}
		
		boolean hasCriterias = !CollectionUtils.isEmpty(criterias);
		//on met @e pour qu'on pourra le remplacer pour deux requêtes différentes
		StringBuilder jpql = new StringBuilder().append("select @e from " + c.getName() + " e ");
		if (hasCriterias) {
			jpql.append(" where ");
			Iterables.forEach(criterias, (i, entry) -> jpql.append(" e." + entry.getKey() + " like concat('%',:"
					+ entry.getKey() + ",'%')" + ((i == (criterias.size() - 1)) ? "" : " and ")));
		}
		
		if(StringUtils.isNotBlank(orderBy)) {
			jpql.append(" order by e."+orderBy);
		}
		
		//requête pour récupérer le numbre totale d'élement selon les critaires
		TypedQuery<Long> queryCount = entityManager.createQuery(jpql.toString().replace("@e", "count(e)"), Long.class);
		if (hasCriterias)
			Iterables.forEach(criterias, (i, entry) -> queryCount.setParameter(entry.getKey(), entry.getValue()));
		Long totelEl = queryCount.getSingleResult();

		//requête pour récupérer les éléments selon la pagination et les critaires
		TypedQuery<E> query = entityManager.createQuery(jpql.toString().replace("@e", "e"), c);
		if (hasCriterias)
			Iterables.forEach(criterias, (i, entry) -> query.setParameter(entry.getKey(), entry.getValue()));

		if (pageNumber != -1 && pageSize != -1) {
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
		}

		Map<String, Object> res = new HashMap<>();
		res.put("totelEl", totelEl);
		res.put("results", query.getResultList());
		return res;

	}
}
