## D287 – CODE CHANGES & LOCATION

**``C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.``**

**mainscreen.html:**
- Line 14: Changed title to "Office Chairs 101"
- Line 19: Changed h1 to "Shop Office Chairs"
- Line 21: Changed h2 to "Office Chair Parts"
- Line 53: Changed h2 to "Office Chair Products" 

**``D. Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.``**

**mainscreen.html:**
- Line 20-21: Added button linking to about page

**about.html (created):**
- Line 1-37: Added content for the about page, including button back to main page
- Line 28: Button to mainscreen

**MainScreenController.java:**
- Line 55-58: Added mapping for about page

**``E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.``**

**BootStrapData.java:**
- Line 42-45: Added logic to clear out products and parts for testing
- Line 49-80: Added logic to check if inhouse-part repository is empty, and then create + add 5 inhouse sample parts
- Line 82-119: Added logic to check if part outsourced-repository is empty, and then create + add 5 outsourced sample parts
- Line 122-134: Added logic to check if product repository is empty, and then create + add 5 sample products

**``F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters: The “Buy Now” button must be next to the buttons that update and delete products, The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts, Display a message that indicates the success or failure of a purchase.``**

**mainscreen.html:**
- Line 86: Added the buy now button with mapping next to update and delete button for products

**BuyProductController.java (created):**
- Line 1-44: Added logic for /buyProduct controller mapping, if > 0 checks and decrements for found productID (will send user to confirm or oos page depending on result) 

**confirmationbuyproduct.html (created):**
- Line 1-10: Added logic to display message to user that purchase was successful, and allow return home

**productoos.html (created):**
- Line 1-10: Added logic to display message to user that product is oos, and allow to return home

