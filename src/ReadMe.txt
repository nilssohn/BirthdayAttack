CSEC 362
Program 2: Birthday Attack
due: 11:55pm 3/20/2018

For given messages x1 and x2, Oscar wants to create two messages
m1 and m2 that satisfy the following properties:

1) hash(m1) == hash(m2)

2) Semantic meaning of original messages x1 and x2 must be preserved.
That is, m1 and m2 should look like x1 and x2 respectively.
Hint: You can create such messages by appending invisible characters (spaces and tab) to the original messages.

3) Effectiveness of your collision search function is the key.
According to the argument of birthday attack, the number of messages (and hash computations) you will need to generate until the first collision found is
O(2^(n/2)), where n is the number of bits in a hash output.

Note: Later when we study digital signatures, you will learn how Oscar can forge a signature using m1 and m2.

-----------------------------------------------------------------------------------------
The starter code contains two classes:

1) Collision.java - a hash function is provided in this class. You need to complete the rest.
2) CollisionTest.java - provided

Expected output using a hash function with 32 bits output:
Elapsed time: 2.214 seconds.
Transfer $10 to Oscar.                                                                                   		   				  :
Transfer $1000 to Oscar.                                                                                     					  		 		 	:
true


Expected output using a hash function with 40 bits of output:
Elapsed time: 7.834 seconds.
Transfer $10 to Oscar.                                                                                  				 		 					 	  :
Transfer $1000 to Oscar.                                                                                		 		  						     :
true