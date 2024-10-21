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

**``G.  Modify the parts to track maximum and minimum inventory by doing the following: Add additional fields to the part entity for maximum and minimum inventory. Modify the sample inventory to include the maximum and minimum fields. Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values. Rename the file the persistent storage is saved to. Modify the code to enforce that the inventory is between or at the minimum and maximum value.``**

**Part.java:**
- Line 34-40: Added additional fields to the part entity for maximum and minimum inventory.
- Line 58-65: Created overloaded constructor which includes the min and max inventory parameters
- Line 115-137: Added setters & getters for both min and max inventory, also added logic to check if values are in bounds
- Line 98-104: Modified setInv method to check if inv is within the bounds of min and max

**BootStrapData.java:**
- Line 53,61,69,77,85: Added Id's for inhouse sample parts
- Line 54,55,62,63,70,71,78,79,86,87,103,104,106,111,112,119,120,127,128,135,136: Added min and max inventory for both inhouse and outsourced parts

**AddInHousePartController.java:**
-Line 45-48: Added logic to make sure inhouse inv does not go above or below what user set (within program bounds) 

**AddOutsourcedPartController.java:**
-Line 45-48: Added logic to make sure outsourced inv does not go above or below what user set (within program bounds) 

**InhousePartForm.html:**
- Line 24: Changes partId to Id so it can be reflected on the form
- Line 26-31: added text inputs for min and max inventory for inhouse parts, also added error message if input out of bounds

**OutsourcedPartForm.html:**
- Line 27-32: added text inputs for min and max inventory for outsourced parts, also added error message if input out of bounds

**InhousePart.java:**
- Line 15,20,24: changed int to Long to prevent null issues and help if longer ids are needed down the line

**InhousePartTest.java:**
- Line 28,35: changed int to Long to prevent conflict with the regular class

**application.properties:**
- Line 6: Renamed the persistent storage file

**``H.  Add validation for between or at the maximum and minimum fields. The validation must include the following: Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts. Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum. Display error messages when adding and updating parts if the inventory is greater than the maximum.``**

>- NOTE: Logic for displaying error messages for updating 'part' inventory going out of bounds was pushed in the last part (see controllers and part.java)

**EnufPartsValidator.java:**
- Line 30-49: Cleaned up logic to check for inventory change when user tries to update 'product' inventory and assocaiated part goes below min bounds

**about.html:**
- Line 19,26: Fixed some errors with brand name

**Part.java:**
- Line 102: Changed the error text so user can differentiate between the 2 errors

**``I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.``**

**PartTest.java:**
- Line 106-138: Added logic for testing if inventory is within bounds and throw error if not

**``J.  Remove the class files for any unused validators in order to clean your code.``**

> - NOTE: All validators are currently in use