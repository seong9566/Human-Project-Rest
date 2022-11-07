package site.metacoding.miniproject.domain.career;

import java.util.List;

public interface CareerDao {
	public void insert(Career career);

	public List<Career> findAll();

	public Career findById(Integer careerId);

	public void update(Career career);

	public void deleteById(Integer careerId);

	public void updateJobPostingBoard(Career career);
}
