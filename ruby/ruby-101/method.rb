def show_name
  puts 'my name'
end
show_name

def square(n)
  puts n**2
end
square(12)

def add(x, y)
  x + y # implicit return
end
puts add(1, 2)

def what_up(greeting, *friends)
  friends.each { |friend| puts "#{greeting}, #{friend}!" }
end

what_up('What up', 'Ian', 'Zoe', 'Zenas', 'Eleanor')
