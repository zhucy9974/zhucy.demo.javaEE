package demo.tool;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.util.CollectionUtils;

public class DBTools {
	public static <E> List<E> getElementsByCriteria(Map<String, String> criterias, Class<E> c, EntityManager entityManager) {
		StringBuffer jpql = new StringBuffer().append("select u from " + c.getName() + " u ");
		if(!CollectionUtils.isEmpty(criterias)) {
			jpql.append(" where ");

			Iterables.forEach(criterias, (i, entry) -> {
				jpql.append(
						" u." + entry.getKey() + "=:" + entry.getKey() + ((i == (criterias.size() - 1)) ? "" : " and "));
			});
			TypedQuery<E> query = entityManager.createQuery(jpql.toString(), c);

			Iterables.forEach(criterias, (i, entry) -> {
				query.setParameter(entry.getKey(), entry.getValue());
			});
			return query.getResultList();
		}else {
			return Collections.emptyList();
		}
		
	}
}
