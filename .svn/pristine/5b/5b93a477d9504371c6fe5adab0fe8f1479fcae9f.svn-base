package com.kintiger.platform.evtstatistics.service.impl;

import java.util.List;

import com.kintiger.platform.evtstatistics.dao.EvtstatisticsDao;
import com.kintiger.platform.evtstatistics.pojo.Evtstatistics;
import com.kintiger.platform.evtstatistics.service.EvtstatisticsService;

public class EvtstatisticsServiceImpl implements EvtstatisticsService{
	private EvtstatisticsDao evtstatisticsDao;

	public EvtstatisticsDao getEvtstatisticsDao() {
		return evtstatisticsDao;
	}

	public void setEvtstatisticsDao(EvtstatisticsDao evtstatisticsDao) {
		this.evtstatisticsDao = evtstatisticsDao;
	}

	@Override
	public int searchOverTimeEvtDtlListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchOverTimeEvtDtlListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchOverTimeEvtDtlList(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchOverTimeEvtDtlList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchEventModelListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchEventModelList(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int createEventModel(Evtstatistics evtstatistics) {
		try {
			Evtstatistics et = new Evtstatistics();
			et.setModelId(evtstatistics.getModelId());
			int count=evtstatisticsDao.searchEventModelListCount(et);
			if(count==0){
				return evtstatisticsDao.createEventModel(evtstatistics);
			}else{
				return evtstatisticsDao.updateEventModel(evtstatistics);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEventModel(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.updateEventModel(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchEventModelForCsv(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelForCsv(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchOverTimeEvtListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchOverTimeEvtListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchOverTimeEvtList(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchOverTimeEvtList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchEventListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchEventList(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Evtstatistics> searchEventModelHrRoles(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelHrRoles(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchEventModelHrListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelHrListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchEventModelHrList(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelHrList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Evtstatistics> searchEventModelHrDetailList(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchEventModelHrDetailList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int createEventModelHr(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.createEventModelHr(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEventModelHr(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.updateEventModelHr(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int searchHrOverTimeEvtDtlListCount(Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchHrOverTimeEvtDtlListCount(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchHrOverTimeEvtDtlList(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchHrOverTimeEvtDtlList(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchSendEmailDateCount() {
		try {
			return evtstatisticsDao.searchSendEmailDateCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int createSendEmailDate(){
		try {
			return evtstatisticsDao.createSendEmailDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Evtstatistics> searchOverTimeEvtDtlForEmail(
			Evtstatistics evtstatistics) {
		try {
			return evtstatisticsDao.searchOverTimeEvtDtlForEmail(evtstatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
