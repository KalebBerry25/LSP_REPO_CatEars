Class: Customer  
Responsibilities:
- Store customer name
- Store customer email
- Provide customer details 
 
Collaborators:
- Order


Class: Order  
Responsibilities:
- Store order item information
- Store base price
- Associate an order with a customer
- Provide order details  

Collaborators:
- Customer
- PricingService


Class: PricingService  
Responsibilities:
- Calculate tax for an order
- Apply discount rules
- Compute final total  

Collaborators:
- Order


Class: ReceiptPrinter  
Responsibilities:
- Format order receipt information
- Print or display a receipt  

Collaborators:
- Order
- PricingService


Class: OrderRepository  
Responsibilities:
- Save order information
- Manage order persistence  

Collaborators:
- Order
- PricingService


Class: NotificationService  
Responsibilities:
- Send confirmation notifications to customers
- Use customer contact information appropriately  

Collaborators:
- Customer
- Order


Class: OrderProcessor  
Responsibilities:
- Coordinate the overall order-processing workflow
- Invoke pricing, persistence, notification, receipt, and logging services in the proper order  

Collaborators:
- Order
- PricingService
- ReceiptPrinter
- OrderRepository
- NotificationService
- OrderLogger