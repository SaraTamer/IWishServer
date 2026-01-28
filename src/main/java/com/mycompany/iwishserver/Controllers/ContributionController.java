package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.AddContributionDTO;
import com.mycompany.iwishserver.DTO.UpdateContributionDTO;
import com.mycompany.iwishserver.Models.Contribution;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.ContributionService;

import java.util.List;

public class ContributionController extends BaseController {

    private ContributionService service = new ContributionService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ================= ADD CONTRIBUTION =================
                case "ADD": {
                    AddContributionDTO dto =
                            gson.fromJson(request.getBody(), AddContributionDTO.class);

                    Contribution contribution = new Contribution();
                    contribution.setGiftId(dto.getGiftId());
                    contribution.setPercentage(dto.getPercentage());

                    int currentUserId =
                            Integer.parseInt(request.getMeta()); // from session / client
                    System.out.println("Current User ID: " + currentUserId);
                    System.out.println(currentUserId);
                    boolean success =
                            service.addContribution(contribution, currentUserId);

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            null,
                            gson.toJson(success)
                    );
                }

                // ================= GET USER CONTRIBUTIONS =================
                case "GET_MY_CONTRIBUTIONS": {
                    int userId = Integer.parseInt(request.getBody());

                    List<Contribution> list =
                            service.getFullUserContributions(userId);

                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(list)
                    );
                }

                // ================= UPDATE CONTRIBUTION =================
                case "UPDATE_PERCENTAGE": {
                    UpdateContributionDTO dto =
                            gson.fromJson(request.getBody(), UpdateContributionDTO.class);

                    boolean success =
                            service.updateContributionPercentage(
                                    dto.getContributionId(),
                                    dto.getNewPercentage()
                            );

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            null,
                            gson.toJson(success)
                    );
                }

                case "GET_BY_GIFT":{
                    int giftId = Integer.parseInt(request.getBody());
                    List<Contribution> list = service.getContributionsByGiftId(giftId);
                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(list)
                    );
                }
                default:
                    return new Response(
                            "ERROR",
                            "Unknown Contribution action",
                            null
                    );
            }

        } catch (Exception e) {
            return new Response(
                    "ERROR",
                    e.getMessage(),
                    null
            );
        }
    }
}
