#Directions to run the program
	- There are three files in the program
		"OrgHierarchyData.txt" - Organization Hierarchy data file
		"UserData.txt" - User data file
		"outputData" - Output data for results, this will be created automatically but will not show in the Package Explorer.
	- Java version 7 was used to develop this.
	- Organization Hierarchy file may have Nulls as a parentOrgId and it's treated as a 0 because it's a parent.
	- Tests to the conversion function can be found in the Tests.java file
	- Tests can be run by opening up JUnit4 the first time by clicking the down arrow next to Run and selecting "Tests". Then once the JUnit test manager is open, click "Re-run all Tests"
	- The program can be run by clicking "Run App", via the green arrow in the menu, if you've ran tests you'll need to switch to "1 app" rather than "2 Tests"

#Description of files
	- App.java 
		- Starting point in the app, it opens the files and organizes it with a Tree, then outputs to OutputData.txt
	- Node.java - Each node in the tree has values (orgId, parentOrgId, name) as well as calculated values like totalNumUsers, totalNumFiles, totalNumBytes to avoid making this calculation when searching. 
				- Though looking back this might have been a bad design decision because the tree self organizes and this value isn't recalculated.
	- Tree.java - Binary search tree with most of the internal public api methods in here because getChildOrgs is a function related to traversing a tree, not data in the Org 
	- Org.java
		- Data assigned to each organization, this is mostly used as a temporary class. I could have improved the design of this for each Node to have a Org (and the attributes would be on this class instead)
	- User.java
		- Data assigned to each user, this is mostly used as a temporary class when inserting data.
	- Helpers.java
		- ConvertToInt is housed here because it could be used in several files later on and I like seeing my main app class as clean as possible.
	- Tests.java
		- Tests! I wrote just a few when wanting to know if outliers were double checked.

#Assumptions
	- Three files should be used. One for organization hierarchy, another for user data, and a third to output the results.
	- 
	
Why did I use the algorithms I did? What alternatives were there?
	- I used a self-balancing binary search tree because it's efficient at find's O(log(n), which most of functional requirements were.
	- Though it might not efficient at inserts because it's O(n) when the tree rotates and self balances and requires extra space for pointers.
	- It's also efficient at printing 0(n)
	 A few options I considered:
		Binary heap - Usually for priority queue, doesn't require space for pointers, searching for an element is O(n)
		Double Linked List - items are linked together through a single next pointer, created very quickly at O(n) and because it has to go through each element, searching is O(n) 
		Hierarchical Clustering - is a set of nested clusters organized like a dendrogram/tree, searches are usually O(n^3), but a limitation with clusters is you can't remove items from clusters and re-organize.
		
#What if there were 500 million rows in the data file? Where would your code break?
	- 500 million rows would require a large virtual size and most likely there will be a OutOfMemoryError.
	- It requires >8gb of ram available, 64 bit Eclipse and 64 bit Windows.
	 
#Would I make any changes? 
	- Yes, I would store all the data into an array first, sort, then store into a binary search tree. This should limit the amount of processing that occurs.
	- This would also be time consuming to test, so I would probably consider using cached or test data if making any other performance improvements

I believe I covered everything I could think of, so feel free to reach out to me if you have any questions, comments or concerns.