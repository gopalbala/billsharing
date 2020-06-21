# Design - Expense / Bill sharing app
App Allows users to share an Expense

Users will be able to organize expenses among multiple heads and share with multiple users.    
Users can create a group.  
Share an expense among the members of group.  
Send notification to on their share to be paid.  
Add bank details for transfer of amount.  
Track paid users.  

**_Use cases_**  

Users should be able to register.  
User creation is idempotent.  
Registered user should be able to create an expense.  
Expense has three states  

   1. Created
   2. Pending
   3. Settled
 
Initial state of the expense would be created.  
Registered user should be able to create expense group i.e. to be able to add users to expenses.  
Bifurcation is custom no need to implement equal sharing.
Once the bifurcation is complete the expense state becomes pending.     
Provision to extend to provide user notification when someone adds them to the expense.  
Users should be able to add their contribution.  
Once the settlement is complete from all the users the expense should become "Settled".    
Any number of users should be able to create expenses at the same time.  
One user should be able to create more than one expense and share it with different set os users.  
Expense creator should be able to track their expenses and payments made by users.  
Users can settle expense in parts.  

The solution should be extendable.  
No need to persist data in database. Data can be stored in memory.

Workflow

User creates an expense  
Add other users  
Share it 
Move Expense state to pending  
Notify  
Users contribute  
Check if the bill is settled   
If so move the expense to settled  
  


