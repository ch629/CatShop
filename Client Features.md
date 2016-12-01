##Back End:
- See a list of products with their quantities
- Add stock to products
##Cashier:
- Add & remove products to the basket
- Check stock of products
- Purchase Order:
    - List of products with quantities
    - Cost of each product with totals
##Customer
- See a list of products:
    - Expand information on click?
    - Show all information next to each product?
    - Show information on hover?
##Shop Display
- Display all Orders waiting 
- Display Orders ready for Collection
##Warehouse
- Show a list of products per order to be collected, then notify when all products have been collected
- Possibly, assign a warehouse client with a specific order (Basically multithreading the orders):
    - This would consist of a new column in the order table containing who it is assigned to, but 
    this would have to be checked when assigned, as if the program is closed with an order still open, 
    that order may still be assigned to no-one; could do checks when verifying the clients are still active (Ping Pong)