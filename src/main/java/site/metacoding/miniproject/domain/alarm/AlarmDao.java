package site.metacoding.miniproject.domain.alarm;

import java.util.List;

public interface AlarmDao {
	public void insert(Alarm alarm);

	public List<Alarm> findAll();

	public Alarm findById(Integer alarmId);

	public void update(Alarm alarm);

	public void updateAlarmByIdToCheck(List<Integer> alarmsId);

	public void deleteById(Integer alarmId);

	public void deleteByPersonalLike(Integer personalLikeId);

	public Boolean findByUsersIdToAlarmChecked(Integer usersId);

	public List<Alarm> findByUsersId(Integer usersId);

	public List<Alarm> findByUsersIdForUnCheckedAlarm(Integer usersId);
}
