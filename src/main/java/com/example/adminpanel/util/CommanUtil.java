package com.example.adminpanel.util;

import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.PageResultModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.PageDetailModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommanUtil {

    public ResponseModel create(String message, Object data, HttpStatus status) {
        ResponseModel rs = new ResponseModel();
        rs.setData(data);
        rs.setMessage(message);
        rs.setStatus(status);
        rs.setStatusCode(status.value());
        return rs;
    }

    public PageResponseModel create(String message, Object data, Object page, HttpStatus status) {
        PageResponseModel rs = new PageResponseModel();
        rs.setData(data);
        rs.setMessage(message);
        rs.setStatus(status);
        rs.setResult(page);
        rs.setStatusCode(status.value());
        return rs;
    }

    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Pageable getPageDetail(PageDetailModel pageDetailModel) {
        /*creating page detail
                 page : PageNo: 0 , Limit: 20,
                                             Sort : Sort.Direction.ASC ,
                                              property :"CreatedAt" {sort on BaseOf})*/
        return PageRequest.of(pageDetailModel.getPage(),
                pageDetailModel.getLimit(),
                (pageDetailModel.getSortorder().equalsIgnoreCase("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC,
                pageDetailModel.getSortFileld()
        );
    }

    public PageDetailModel fillValueToPageModel(PageDetailModel pageDetailModel) {
        if (checkNull(pageDetailModel.getPage())) {
            pageDetailModel.setPage(0);
        }
        if (checkNull(pageDetailModel.getLimit())) {
            pageDetailModel.setLimit(20);
        }
        if (checkNull(pageDetailModel.getSortorder())) {
            pageDetailModel.setSortorder("DESC");
        }
        if (checkNull(pageDetailModel.getSortFileld())) {
            pageDetailModel.setSortFileld("created_at");
        }

        return pageDetailModel;
    }

    public PageResultModel pagersultModel(Page page) {
        PageResultModel model = new PageResultModel();
        model.setTotalCount(page.getTotalElements());
        model.setPageno(page.getNumber());
        model.setPagecount(page.getTotalPages());

        return model;
    }

    public boolean checkNull(String str) {
        return str == null || str.equals("");
    }

    private boolean checkNull(Integer no) {
        return no == null;
    }

    public String trimLastComma(String str) {
        if (str != null)
            return str.substring(0, str.length() - 1);
        else
            return null;
    }
}
