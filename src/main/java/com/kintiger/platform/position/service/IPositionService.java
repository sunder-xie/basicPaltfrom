package com.kintiger.platform.position.service;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.pojo.Bposition;

/**
 * �ӿ�<br>
 * ����λ�S�o ����ְλ���� һЩ��ԓ���F���ֵ�ӿڷ���
 * 
 */
public interface IPositionService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public int getPositionCount(Bposition position);

	/**
	 * 
	 * @param bposition
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
	 * taobд�ģ�����orgId�õ�����֯���µı���ְλ����
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getStaffamountPositionList(Long orgId);

	/**
	 * ����orgId�õ�����֯�µı���ְλ����
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getAllStaffamountPosition(Long orgId);

	/**
	 * ����ְλ
	 * 
	 * @param bposition
	 * @return
	 */
	public String createPosition(Bposition bposition);

	/**
	 * �޸�ְλ
	 * 
	 * @param bposition
	 * @return
	 */
	public Boolean updatePosition(Bposition bposition);

	/**
	 * ɾ��ְλ(�����)
	 * 
	 * @param bposition
	 * @return
	 */
	public String deletePosition(Long posId);

	/**
	 * ��ȡְλ����
	 * 
	 * @param position
	 * @return
	 */
	public Bposition getPositionObj(Bposition position);

	/**
	 * �ͷ�ְλ����
	 * 
	 * @param bposition
	 * @return
	 */
	public String releasePosition(Bposition position);

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
	 * �ж�ְλ�Ƿ����豸
	 * 
	 * @param posId
	 * @return
	 */
	public int getDeviceNum(Long posId);

	/**
	 * ���ݲ�����ȡְλ��Ϣ ��ѯӳ��ְλ
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getPosition(Bposition bposition);

	public Boolean updatePosition4User(Bposition bposition);

	public Boolean updatePosition4Position(Bposition bposition);

	/**
	 * ����Use_State����ְλ����
	 * 
	 * @param bposition1
	 * @return
	 */
	public Bposition getPositionByUseState(Bposition bposition1);

	public List<Bposition> getPositionListByUseState(Bposition bposition1);

}
