package com.ozorkin.action;

import com.ozorkin.analytics.DataAnalytics;
import com.ozorkin.service.ShopService;

public interface Action {
    ShopService shopService = ShopService.getInstance();
    DataAnalytics dataAnalytics = DataAnalytics.getInstance();


    void execute() throws Exception;
}

