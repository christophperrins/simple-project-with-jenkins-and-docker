Get the login stuff running first

ie eureka, login, token and account

What happens is that everytime a login action is authenticated a new token is sent back to authenticate the next action - this means that the lifetime of the tokens are incredibly short


When the main notes webpage is accessed at localhost/home.html (redirected from nginx)
It will kick you back to the login screen if you dont have a bearerToken

When you have login in it will send to your machine a new bearer token, which is saved via sessionStorage



When you make a request, this token is sent with it. 
the card service will send a request to the login service as to whether this person is authenticated;





