my_array = [1, 2, 3]
my_array.push(4)
my_array << 5 # same as above
print my_array
print (1..3).to_a
puts

multi_d_array = [
  [0, 0, 0, 0],
  [0, 0, 0, 0],
  [0, 0, 0, 0],
  [0, 0, 0, 0]
]
multi_d_array.each { |x| puts "#{x}\n" }
