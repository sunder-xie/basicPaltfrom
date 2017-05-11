package com.kintiger.platform.position.dao;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.pojo.Bposition;

public interface IPositionDao {

	/**
	 * 
	 * @param position
	 * @return
	 */
	public int getPositionCount(Bposition position);

	/**
	 * 
	 * @param position
	 * @return
	 */
	public List<Bposition> getPositionList(Bposition position);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * taobд�ģ��߼�����
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getStaffamountPositionList(Long orgId);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getAllStaffamountPosition(Long orgId);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public Long createPosition(Bposition bposition);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public int updatePosition(Bposition bposition);

	/**
	 * �жϸ�ְλ���Ƿ��йҿͻ�
	 * 
	 * @param bposition
	 * @return
	 */
	public int getPostCustNum(Long posId);

	/**
	 * �жϸ�ְλ���Ƿ��й��豸
	 * 
	 * @param bposition
	 * @return
	 */
	public int getDeviceNum(Long posId);

	/**
	 * ɾ���ɹ���Ҫ������cust���߼���tub
	 * 
	 * @param posId
	 * @return
	 */
	public int updateCustPos(Long posId);

	/**
	 * ��ȡְλ����
	 * 
	 * @param position
	 * @return
	 */
	public Bposition getPositionObj(Bposition position);

	/**
	 * ���ذ��´������ְλ�б�
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getManagementList(Long orgId);

	/**
	 * �ѷ����ϼ���ְλ
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getAssignedPosList(Long posId);

	/**
	 * δ�����ϼ���ְλ
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getUnassignedPosList(Long orgId);

	/**
	 * taobд�ģ��߼�����
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getStaffamountPositionPosList(Bposition bposition);

	/**
	 * ����empId��ȡְλ��λ��Ϣ�����̣�
	 * 
	 * @param empId
	 * @return
	 */
	public List<Bposition> getPositionsByEmpId(Long empId);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getPosition(Bposition bposition);

	
	public int updatePosition4User(Bposition bposition);
	
	
	
	public int updatePosition4Position(Bposition bposition);

	/**
	 * ����Use_State����ְλ����
	 * @param bposition1
	 * @return
	 */
	public Bposition getPositionByUseState(Bposition bposition1);

	public List<Bposition> getPositionListByUseState(Bposition bposition1);

}
