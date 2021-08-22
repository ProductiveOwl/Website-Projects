def check_user_input (user_num):
  x = False
  while x == False:
    #isdigit() only works for positive numbers
    #multiply by -1 to turn negative -> positive
    if user_num.isdigit():
      neg_pos = input("Would you like to find the factors for " + user_num + " or its negative? (type 'y' for its negative and anything else for positive): ")
      if neg_pos == "y" or neg_pos == "Y":
        new_user = int(user_num) * -1
        user_num = str(new_user)
      x = True
    else:
      print ("Invalid Input")
      user_num = input("Input an integer to find factors: ")
  return user_num

#Start program
again = "y"
print ("Factor Finder Program")
while again == "y" or again == "Y":
  user_num = input("Input a positive integer to find its factors: ")
  user_num = check_user_input(user_num)
  i = 2
  factors = ""
  #Finds Factors by checking if the remainder is 0
  #Also includes negative factors
  while i <= abs((int(user_num) / 2)):
    if int(user_num) % i == 0:
      factors += str(i) + ", "
      if int(user_num) < 0:
        factors += str(i * -1) + ", "
    i += 1

  print ("\nHere are the factors for " + user_num + ":")

  #Edge cases and answers
  if (int(user_num) == 0):
    print ("The only factor of 0 is 0.")
  elif (int(user_num) == 1):
    print ("The only factor of 1 is 1.")
  elif (int(user_num) == -1):
    print ("The only factors of -1 are 1 and -1.")
  elif (int(user_num) < 0):
    print ("1, -1, " + factors + str(int(user_num) * -1) + ", " + user_num)
  else: 
    print ("1, " + factors + user_num)

  again = input("\nWould you like to try another integer?\n('y' for yes and any other key for no): ")

print("Bye")
