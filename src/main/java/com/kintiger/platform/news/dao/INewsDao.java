package com.kintiger.platform.news.dao;

import java.util.List;

import com.kintiger.platform.news.pojo.IsRead;
import com.kintiger.platform.news.pojo.NewsDetail;
import com.kintiger.platform.news.pojo.NewsFile;
import com.kintiger.platform.news.pojo.NewsRecord;
import com.kintiger.platform.news.pojo.NewsTotal;
import com.kintiger.platform.news.pojo.Organ;

public interface INewsDao {

	/**
	 * ��ȡoa��������
	 * 
	 * @return
	 */
	public List<NewsDetail> getNewsList(NewsDetail lanNewsDelailbean);

	/**
	 * ��ȡ�����ܱ���Ϣ
	 * 
	 * @return
	 */
	public List<NewsTotal> getNewsTotalList();

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public NewsDetail getNewsDetail(NewsDetail NewsDetail);

	/**
	 * �������ŵ������
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int updateNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public List<NewsFile> getNewsFileList(NewsFile NewsFile);

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal);

	/**
	 * ���������ܱ�lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public Long createNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ�����
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ��ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail);

	/**
	 * ɾ����ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int deleteNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsDetail(NewsDetail lanNewsDetail);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsFile(NewsFile newsFilebean);

	/**
	 * ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int updateNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * �޸�������ϸ������
	 * 
	 * @param newsFilebean
	 * @return
	 */
	public int updateNewsFile(NewsFile newsFilebean);

	/**
	 * ��ѯ���ݿ����Ƿ��Ѿ�����ΪͼƬ��ʾ������
	 */
	public int getTotalShowCount();

	/**
	 * ��¼�����¼
	 */
	public Long addRecord(NewsRecord r);

	/**
	 * ��¼���ʱ��
	 */
	public void recordScanTime(NewsRecord r);

	/**
	 * ��ѯ��¼
	 * 
	 * @param r
	 * @return
	 */
	public List<NewsRecord> searchScanRecord(NewsRecord r);

	/**
	 * �鿴�������Ƿ�ռ��
	 */
	public int blurNewsCode(NewsTotal newsTotal);
	/**
	 * MethodsTitle: �����Ƿ��Ķ��ı�ʶ
	 * @author: xg.chen
	 * @date:2016��12��14�� ����5:06:58
	 * @version 1.0
	 * @param isRead
	 * @return
	 */
	public void insertIsRead(IsRead isRead);
	/**
	 * MethodsTitle: ������ϸID��ȡ
	 * @author: xg.chen
	 * @date:2016��12��15�� ����8:43:32
	 * @version 1.0
	 * @return
	 */
	public IsRead getIsRead(IsRead isRead);
	/**
	 * MethodsTitle: �����м����ݱ�����
	 * @author: xg.chen
	 * @date:2017��1��19�� ����2:54:08
	 * @version 1.0
	 * @param organ
	 * @return
	 */
	public Long createOrgan(Organ organ);

	public List<NewsDetail> getNewsList1(NewsDetail lanNewsDelailbean);
	/**
	 * MethodsTitle: ɾ�����Ÿ���
	 * @author: xg.chen
	 * @date:2017��2��17�� ����2:36:38
	 * @version 1.0
	 * @param newsFilebean
	 * @return
	 */
	public Long deleteNewsFile(NewsFile newsFilebean);


}
