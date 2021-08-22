import random

#Rules of the game
def say_rules():
  print ("")
  print ("Here are the rules!")
  print ("Rock beats scissors, scissors beats paper and paper defeats rock.")
  print ("Pick between 1, 2 and 3 to choose your object.")
  print ("The computer will also choose a random number between 1 and 3.")
  print ("You have 10 tries. Try to score as many points as you can by defeating the computer!")
  print ("")

#Checks if input is valid
def check_user_input(user_choice):
  new_num = int(user_choice)
  while not(int(new_num) > 0 and int(new_num) < 4):
    new_num = input("Invalid choice. Pick a number between 1 and 3: ")
  return new_num

#Generates computer's choice
def comp_turn():
  comp_num = random.randint(1, 3)
  return comp_num

#Checks the winner and returns the winning message and the points
# 0 = Tied, 1 = user wins, -1 = computer wins
def check_winner(comp_number, user_num):
  if comp_number == user_num:
    return 0
  elif comp_number == "3" and user_num == "1":
    return 1
  elif comp_number == "1" and user_num == "2":
    return 1
  elif comp_number == "2" and user_num == "3":
    return 1
  else:
    return -1

#Introduction
print ("Welcome to Rock Paper Scissors!")
rules = input("Would you like to know the rules?(type \"y\" for yes): ")
if rules == "y":
  say_rules()

#Start game
total_points = 0
attempts = 1
while attempts <= 10:
  print ("Game: " + str(attempts))
  print ("Here are your choices: ")
  print ("1 = Rock     2 = Paper     3 = Scissors")
  user_choice = input("What do you choose?: ")
  new_choice = check_user_input(user_choice)
  comp_number = comp_turn()
  winner_and_points = check_winner(str(comp_number), str(new_choice))
  
  #Reveal results
  print ("Computer chose " + str(comp_number) + " and you chose " + str(new_choice))
  if winner_and_points == 1:
    print ("You won! You gained 1 point.")
    total_points += 1
  elif winner_and_points == 0:
    print ("You tied!")
  elif winner_and_points == -1:
    print ("You lost!")
  
  print ("Total Points: " + str(total_points))
  print ("")
  attempts += 1

#End of the game
print ("Thanks for playing!")
print ("You scored a total of " + str(total_points) + " points!")
