package demo.tool;

import java.util.List;

@FunctionalInterface
public interface TransferEntity<T>  {
	void transferEntityToEntityV(Object obj, List<T> entityVList);
}
