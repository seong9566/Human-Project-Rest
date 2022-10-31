package site.metacoding.miniproject.domain.users;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UsersDao {
	public void insert(Users users);

	public List<Users> findAll();

	public Users findById(Integer id);

	public Users findByIdAndPassword(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword);

	public Users findByResumesId(Integer resumesId);

	public Users findByCompanyId(Integer companyId);

	public Integer findByLoginId(String loginId);

	public void update(Users users);

	public void deleteById(Integer id);

}
