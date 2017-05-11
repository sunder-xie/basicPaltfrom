package com.kintiger.platform.menu.service;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.menu.pojo.Menu;

/**
 * �ˆνӿ�<br>
 * �����ˆξS�o ��ɫ�ˆξS�o
 * 
 * @author xujiakun
 * 
 */
public interface IMenuService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ��,���Ժ�����!";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ��,��������!";

	public static final String ERROR_NULL_MESSAGE = "����ʧ��,�����Ѳ�����!";

	public static final String MENU_REDIRECT_URL = "/menuAction!redirectMenu.jspa?node=";

	/**
	 * �˵���
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuTreeList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int getMenuCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuList(Menu menu);

	/**
	 * �����ˆ�
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult createMenu(Menu menu);

	/**
	 * �޸Ĳˆ�
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult updateMenu(Menu menu);

	/**
	 * ����id��ѯ�˵���Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Menu getMenuById(Long id);

	/**
	 * ����menu��ѯ�˵���Ϣ
	 * 
	 * @param menu
	 * @return
	 */
	public Menu getMenu(Menu menu);

	/**
	 * �h���ˆ�
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult deleteMenu(Menu menu);

	/**
	 * ��ѯ��ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	public int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * ��ѯ��ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * ѡ���ɫ�˵�
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult selectMenu4Role(Menu menu);

	/**
	 * ɾ����ɫ�����˵�
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult deleteSelectedMenu4Role(Menu menu);

	/**
	 * ģ������menu
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> blurSearchMenu(Menu menu);

	/**
	 * ά�������¼����
	 * 
	 * @param menu
	 */
	public void createOrUpdateSso(Menu menu);

	/**
	 * �˵������־
	 * 
	 * @param menu
	 */
	public void menuClickLog(Menu menu);

}
