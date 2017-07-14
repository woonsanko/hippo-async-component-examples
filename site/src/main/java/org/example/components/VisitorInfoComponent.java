package org.example.components;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

public class VisitorInfoComponent extends BaseHstComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        super.doBeforeRender(request, response);

        String userAgent = StringUtils.defaultString(request.getHeader("User-Agent"));
        Calendar now = Calendar.getInstance();

        request.setAttribute("userAgent", userAgent);
        request.setAttribute("now", now);
    }
}
