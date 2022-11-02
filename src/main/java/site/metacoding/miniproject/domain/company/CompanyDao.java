package site.metacoding.miniproject.domain.company;

import java.util.List;

import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyInfoRespDto;
import site.metacoding.miniproject.web.dto.response.company.CompanyAddressDto;

public interface CompanyDao {
	public void insert(Company company);

	public List<Company> findAll();

	public Company findById(Integer companyId);

	public void update(Company company);

	public void deleteById(Integer id);

	public CompanyInfoRespDto companyInfo(Integer companyId);

	public CompanyAddressDto findByAddress(Integer companyId);

}
