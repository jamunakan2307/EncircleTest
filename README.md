S-expression calculator
 A command line program that acts as a simple calculator: it takes a single argument as an expression and prints out the integer result of evaluating it.
 
 Test: 
 Simple Junit Parameterized test for both positive and negative scenario as stated below :
 
 (add 1 1)
 => 2
__________________________________________________________
(add 0 (add 3 4))
 => 7
__________________________________________________________
(add 3 (add (add 3 3) 3))
 => 12
__________________________________________________________
(multiply 1 1)
 => 1
__________________________________________________________
(multiply 0 (multiply 3 4))
 => 0
__________________________________________________________
(multiply 2 (multiply 3 4))
 => 24
__________________________________________________________
(multiply 3 (multiply (multiply 3 3) 3))
 => 81
__________________________________________________________
(add 1 (multiply 2 3))
 => 7
__________________________________________________________
(multiply 2 (add (multiply 2 3) 8))
 => 28
__________________________________________________________
123
 => 123
__________________________________________________________
0
 => 0
__________________________________________________________


