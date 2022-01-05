package restaurant.controller.fooddelivery;

import restaurant.view.fooddelivery.IFoodServiceView;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food service
 */
public interface IFoodService extends IFood {
    void selectFoodService(IFoodServiceView foodServiceView);
    int getServiceID();
}
