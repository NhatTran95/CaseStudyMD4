package com.cg.service.stylist;

import com.cg.model.Stylist;
import com.cg.model.Stylist;
import com.cg.service.IGeneralService;

public interface IStylistService extends IGeneralService<Stylist,Long> {
    Stylist create(Stylist hairService);

    Stylist update(Stylist hairService);

}
