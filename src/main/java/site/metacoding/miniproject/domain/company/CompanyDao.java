package site.metacoding.miniproject.domain.company;

import java.util.List;

import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyAddressRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailWithPerRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateFormRespDto;

public interface CompanyDao {
	public void insert(Company company);

	public List<Company> findAll();

	public Company findById(Integer companyId);

	public void update(Company company);

	public void deleteById(Integer id);

	public CompanyDetailRespDto findByCompany(Integer companyId);

	public CompanyDetailWithPerRespDto findByCompanyToPer(Integer companyId);

	public CompanyAddressRespDto findByAddress(Integer companyId);

	public CompanyUpdateFormRespDto companyUpdateById(Integer companyId);
}
