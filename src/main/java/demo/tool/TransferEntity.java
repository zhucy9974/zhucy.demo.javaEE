package demo.tool;

import java.util.List;

@FunctionalInterface
public interface TransferEntity<T>  {
	void TransferEntityToEntityV(Object obj, List<T> entityVList);
}
