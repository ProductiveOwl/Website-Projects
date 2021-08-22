import random

def draw_initial_board ():
  board = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
  print (str(board[:3]) + "\n" + str(board[3:6]) + "\n" + str(board[6:]))

def check_user_input (user_choice, position_check):
  x = False
  while x == False:
    if user_choice.isdigit():
      #Out of range
      if int(user_choice) > 9 or int(user_choice) <= 0:
        user_choice = input ("Invalid choice. Pick another number: ")
      #Already selected
      elif user_choice in position_check:
        user_choice = input ("That position has already been chosen. Pick another number: ")
      else:
        x = True
    #Didn't pick a number
    else:
      user_choice = input ("Choose a valid number: ")
  return (user_choice)
  
def find_winner (bo_row_1, bo_row_2, bo_row_3, board):
  # 0 == No Winner, 1 == User wins, 2 == computer wins
  total = 0
  #Seperate rows, columns and diagonals into lists to check winner
  User_wins = ["X", "X", "X"]
  Comp_wins = ["O", "O", "O"]
  Col_1 = [bo_row_1[0], bo_row_2[0], bo_row_3[0]]
  Col_2 = [bo_row_1[1], bo_row_2[1], bo_row_3[1]]
  Col_3 = [bo_row_1[2], bo_row_2[2], bo_row_3[2]]
  #Diagonals
  le_to_ri = [bo_row_1[0], bo_row_2[1], bo_row_3[2]]
  ri_to_le = [bo_row_1[2], bo_row_2[1], bo_row_3[0]]
  #Check the rows
  if User_wins == bo_row_1 or User_wins == bo_row_2 or User_wins == bo_row_3:
    total += 1
  elif Comp_wins == bo_row_1 or Comp_wins == bo_row_2 or Comp_wins == bo_row_3:
    total += 2
  #Check the columns
  elif User_wins == Col_1 or User_wins == Col_2 or User_wins == Col_3:
    total += 1
  elif Comp_wins == Col_1 or Comp_wins == Col_2 or Comp_wins == Col_3:
    total += 2
  #Check diagonals
  elif User_wins == le_to_ri or User_wins == ri_to_le:
    total += 1
  elif Comp_wins == le_to_ri or Comp_wins == ri_to_le:
    total += 2
  #If there is no winner
  else:
    pass
  return total

def draw_new_board (comp_choices, user_choices):
  board = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
  #Place markers for the user choice
  for i in user_choices:
    place = int(i) - 1
    board[place] = "X"
  #Place markers for the computer choice
  for i in comp_choices:
    place = int(i) - 1
    board[place] = "O"
  #Redraw Board
  board_row_1 = board[:3]
  board_row_2 = board[3:6]
  board_row_3 = board[6:]
  full_board = str(board_row_1) + "\n" + str(board_row_2) + "\n" + str(board_row_3)
  #Check Winner
  winner = find_winner(board_row_1, board_row_2, board_row_3, board)
  if winner > 0:
    if winner == 1:
      print ("\nYou win!")
    else:
      print ("\nThe Computer Wins.")
  return full_board, winner

def comp_turn (player_positions, turns):
  places = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
  #Remove choices that were already chosen
  for i in player_positions:
    places.remove(i)
  #Choose a number from available options
  comp_num = random.choice(places)
  return comp_num

#Start the game
print ("Welcome to Tic Tac Toe!")
say_rules = input("Would you like to know the rules?(type y for yes and any other key to skip): ")

if say_rules == "y":
  print ("\nHere are the rules:")
  print ("You are the marker 'X' and the computer 'O'.")
  print("Pick a number on the grid to place your marker (You go first!).")
  print ("First to place their marker in a line (row, column or diagonal) wins!\n")

player_positions = []
user_choices = []
comp_choices = []
draw_initial_board()

turns = 1
while turns <= 5:
  print ("\n" + "Turn: " + str(turns))
  user_input = input ("Pick a number: ")

  position = check_user_input(user_input, player_positions)
  player_positions.append(position)
  user_choices.append(position)

  #Check if player wins before computer choses a number
  user_plays, winner = draw_new_board(comp_choices, user_choices)
  if turns != 5 and winner == 0:
    comp_goes = comp_turn(player_positions, turns)
    player_positions.append(comp_goes)
    comp_choices.append(comp_goes)
    print ("\nThe computer chose " + comp_goes)
    user_plays, winner = draw_new_board(comp_choices, user_choices)
  print (user_plays)

  #Stop loop if there is a winner
  if winner > 0:
    turns = 6
  if turns == 5: 
    print ("It's a draw!")
  turns += 1

print ("\nThanks for playing!")
