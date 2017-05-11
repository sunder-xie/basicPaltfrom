package com.kintiger.platform.news.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.news.dao.INewsDao;
import com.kintiger.platform.news.pojo.IsRead;
import com.kintiger.platform.news.pojo.NewsDetail;
import com.kintiger.platform.news.pojo.NewsFile;
import com.kintiger.platform.news.pojo.NewsRecord;
import com.kintiger.platform.news.pojo.NewsTotal;
import com.kintiger.platform.news.pojo.Organ;
import com.kintiger.platform.news.service.INewsService;

public class NewsServiceImpl implements INewsService {

	private Log logger = LogFactory.getLog(NewsServiceImpl.class);

	private INewsDao newsDao;

	public List<NewsDetail> getNewsList(NewsDetail lanNewsDelailbean) {
		try {
			return newsDao.getNewsList(lanNewsDelailbean);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * 获取新闻总表信息
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalList() {
		try {
			return newsDao.getNewsTotalList();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * 获取新闻明细
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * 获取新闻明细
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public NewsDetail getNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.getNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return null;
	}

	/**
	 * 更新新闻点击次数
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int updateNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.updateNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return 0;
	}

	/**
	 * 获取新闻明细附件
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public List<NewsFile> getNewsFileList(NewsFile NewsFile) {
		try {
			return newsDao.getNewsFileList(NewsFile);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsFile), e);
		}

		return null;
	}

	/**
	 * 获取父级栏目
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTreeTypeList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * 插入新闻总表lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public Long createNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.createNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}
		return null;
	}

	/**
	 * 获取新闻总表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotalJsonCount(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * 获取新闻总表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotalJsonList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * 获取新闻明细表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail) {
		try {
			return newsDao.getNewsDetailJsonCount(lanNewsDetail);
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 获取新闻明细表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail) {
		try {
			return newsDao.getNewsDetailJsonList(lanNewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsDetail), e);
		}

		return null;
	}

	/**
	 * 删除明细
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int deleteNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.deleteNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return 0;
	}

	/**
	 * 获取新闻明细表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailMoreListCount(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * 获取新闻明细表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailMoreList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * 插入新闻明细表lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsDetail(NewsDetail lanNewsDetail) {
		try {
			return newsDao.createNewsDetail(lanNewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsDetail), e);
		}

		return null;
	}

	/**
	 * 插入新闻明细表lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsFile(NewsFile newsFilebean) {
		try {
			return newsDao.createNewsFile(newsFilebean);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFilebean), e);
		}

		return null;
	}

	/**
	 * 更新新闻总表
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int updateNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.updateNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * 获取单个新闻总表
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * 修改新闻明细附件表
	 * 
	 * @param newsFilebean
	 * @return
	 */
	public int updateNewsFile(NewsFile newsFilebean) {
		try {
			return newsDao.updateNewsFile(newsFilebean);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFilebean), e);
		}

		return 0;
	}

	/**
	 * 记录点击
	 * 
	 */
	public Long addRecord(NewsRecord r) {
		try {
			return newsDao.addRecord(r);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(r), e);
			return 0L;
		}
	}

	/**
	 * 记录浏览时间
	 */
	public void recordScanTime(NewsRecord r) {
		try {
			newsDao.recordScanTime(r);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public int getTotalShowCount() {
		try {
			return newsDao.getTotalShowCount();
		} catch (Exception e) {
			logger.error(e);
		}
		return 1;
	}

	public List<NewsRecord> searchScanRecord(NewsRecord r) {
		try {
			return newsDao.searchScanRecord(r);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int blurNewsCode(NewsTotal newsTotal) {
		try {
			return newsDao.blurNewsCode(newsTotal);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public void insertIsRead(IsRead isRead) {
		try {
			newsDao.insertIsRead(isRead);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public IsRead getIsRead(IsRead isRead) {
		try {
			return newsDao.getIsRead(isRead);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long createOrgan(Organ organ) {
		try {
			return newsDao.createOrgan(organ);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<NewsDetail> getNewsList1(NewsDetail lanNewsDelailbean) {
		try {
			return newsDao.getNewsList1(lanNewsDelailbean);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long deleteNewsFile(NewsFile newsFilebean) {
		try {
			return newsDao.deleteNewsFile(newsFilebean);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}



}
