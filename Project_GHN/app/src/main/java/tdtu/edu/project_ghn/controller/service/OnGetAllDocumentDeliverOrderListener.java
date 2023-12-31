package tdtu.edu.project_ghn.controller.service;

import java.util.Map;

import tdtu.edu.project_ghn.entity.DeliverOrder;

public interface OnGetAllDocumentDeliverOrderListener {
    void onSuccess(Map<String, Map<String, DeliverOrder>> collectionOrders);
    void onFailure(String err);
}
