This is an implementation of the canonical DCI money transfer example, using Java 8 and Lambda functions.

____

Hi guys, 

I've been playing around with the functional support in Java 8, and 
wrote up an attempt at expressing the DCI money transfer example using 
functions. All methods are parameter-less and produce functions, and all 
state is immutable (accounts and context state). 

The code can be found here:    
https://github.com/rickardoberg/dcifun/tree/master/src 

For laziness sake, here is the test client code that invokes it: 

~~~~
Account from = new Account(100); 
Account to = new Account(0); 
MoneyTransfer moneyTransfer = new MoneyTransfer(from, to); 

moneyTransfer = MoneyTransfer.bind(). // Context binding function 
                               apply(moneyTransfer). // Bind to state 
                               apply(new MoneyTransfer.Transfer(50)); 
// Resulting money transfer has new state. Old state is untouched 

assertThat(moneyTransfer.getFrom().getBalance(), equalTo(50)); 
assertThat(moneyTransfer.getTo().getBalance(), equalTo(50)); 
.... 

bind() looks like this: 
public static Function<MoneyTransfer, 
               Function<Transfer,MoneyTransfer>> bind() 
     { 
         return moneyTransfer -> transfer -> MoneySource.transfer(). 
                 apply(moneyTransfer.getFrom(), 
                    MoneySink.transferTo().apply(moneyTransfer.getTo())). 
                       apply(transfer); 
     } 
~~~~

The MoneySource role within the MoneyTransfer context looks like this: 

~~~~
private static class MoneySource 
{ 
    static BiFunction<Account, Function<Transfer, Account>, 
                      Function<Transfer, MoneyTransfer>> transfer() 
    { 
       return (account, moneySink) -> transfer -> 
          new MoneyTransfer(withdraw(). 
                            apply(account). 
                            apply(transfer.getAmount()), 
                            moneySink.apply(transfer)); 
    } 
} 
....
~~~~

And so on... check GitHub for details. 

This works with Java 8, Lambda edition. Run as-is or load up in IntelliJ 
12 as I did. 

The resulting amount of code is ridiculously small. Probably takes some 
time to get used to reading all the fun-fun code, but other than that it 
was pretty straightforward. I'm not a functional programmer per se, but 
wrote this example up in about half an hour. 

regards, Rickard 

