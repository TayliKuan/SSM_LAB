package com.reportt.model;

import java.util.List;
import java.util.Set;

public interface ReporttDAO_interface {
	public void insert(ReporttVO reporttVO);
    public void update(ReporttVO reporttVO);
    public void delete(String repno);
    public ReporttVO findByPrimaryKey(String repno);
    public List<ReporttVO> getAll();
}
