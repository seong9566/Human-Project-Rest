package site.metacoding.miniproject.domain.apply;

import java.util.List;

public interface ApplyDao {
	public void insert(Apply apply);
	public List<Apply> findAll();
	public Apply findById(Integer id);
	public void update(Apply apply);
	public void deleteById(Integer id);
}
