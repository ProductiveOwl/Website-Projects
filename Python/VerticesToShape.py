import turtle

turtle.title("Vertices to Shape")
t = turtle.Turtle()
t.clear()
t.shape("circle")
i = 1
again = "y"

print("Welcome to Vertices to Shape Converter!")
while again == "y":
  vertices = input("How many vertices does your shape have?: ")
  #Check if input is a number
  if vertices.isdigit():
    num_vertices = int(vertices)
    if num_vertices > 2:
      #Calculations
      sum_interior_angle = (int(vertices) - 2) * 180
      print("Sum of interior angles: " + str(sum_interior_angle))
      interior_angle = sum_interior_angle / int(vertices)
      print("Interior angle: " + str(interior_angle))
      exterior_angle = 180 - interior_angle
      print("Exterior angle: " + str(exterior_angle))
      #Drawing
      for i in range(int(vertices)):
        t.forward(100)
        t.left(exterior_angle)
        t.stamp()      
        i = i + 1
    else: print ("N/A")
  else: print("N/A")
  again = input("Would you like to try a different shape?('y' for yes and any other key for no): ")
  if again == "y":
    t.clear()
print("Program has ended.")
turtle.done()
