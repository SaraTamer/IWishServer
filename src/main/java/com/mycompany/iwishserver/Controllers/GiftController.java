package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.GiftActionDTO;
import com.mycompany.iwishserver.Models.Gift;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.GiftService;

import java.util.List;

public class GiftController extends BaseController {

    private GiftService service = new GiftService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ========== ADD GIFT ==========
                case "ADD_GIFT": {
                    GiftActionDTO dto = gson.fromJson(request.getBody(), GiftActionDTO.class);
                    Gift gift = new Gift();
                    gift.setGiftName(dto.getGiftName());
                    gift.setPrice(dto.getPrice());
                    gift.setOwnerUserId(dto.getOwnerUserId());
                    gift.setStatus(dto.getStatus());

                    String result = service.addGift(gift);
                    return new Response(
                            "SUCCESS".equals(result) ? "SUCCESS" : "ERROR",
                            result,
                            null
                    );
                }

                // ========== GET USER GIFTS ==========
                case "GET_USER_GIFTS": {
                    int userId = Integer.parseInt(request.getBody());
                    List<Gift> gifts = service.getUserGifts(userId);
                    return new Response("SUCCESS", null, gson.toJson(gifts));
                }

                // ========== DELETE GIFT ==========
                case "DELETE_GIFT": {
                    GiftActionDTO dto = gson.fromJson(request.getBody(), GiftActionDTO.class);
                    String result = service.deleteGift(dto.getGiftId(), dto.getOwnerUserId());
                    return new Response(
                            "SUCCESS".equals(result) ? "SUCCESS" : "ERROR",
                            result,
                            null
                    );
                }

                // ========== UPDATE GIFT ==========
                case "UPDATE_GIFT": {
                    GiftActionDTO dto = gson.fromJson(request.getBody(), GiftActionDTO.class);
                    Gift gift = new Gift();
                    gift.setGiftId(dto.getGiftId());
                    gift.setGiftName(dto.getGiftName());
                    gift.setPrice(dto.getPrice());
                    gift.setStatus(dto.getStatus());
                    gift.setOwnerUserId(dto.getOwnerUserId());

                    boolean success = service.updateGift(gift);
                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Gift updated successfully" : "Failed to update gift",
                            null
                    );
                }

                // ========== GET GIFT BY ID ==========
                case "GET_GIFT_BY_ID": {
                    int giftId = Integer.parseInt(request.getBody());
                    Gift gift = service.getGiftById(giftId);
                    if (gift != null) {
                        return new Response("SUCCESS", null, gson.toJson(gift));
                    } else {
                        return new Response("ERROR", "Gift not found", null);
                    }
                }

                default:
                    return new Response("ERROR", "Unknown GIFT action", null);
            }
        } catch (Exception e) {
            return new Response("ERROR", e.getMessage(), null);
        }
    }
}
