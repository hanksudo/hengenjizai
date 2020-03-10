# yield
def block_test
  puts "We're in the method!"
  puts 'Yielding to the block...'
  yield
  puts "We're back in the method!"
end

block_test { puts ">>> We're in the block!" }

# yield with parameter
def yield_name(name)
  puts "In the method! Let's yield."
  yield('Kim')
  puts 'In between the yields!'
  yield(name)
  puts 'Block complete! Back in the method.'
end

yield_name("Hank") { |n| puts "My name is #{n}." }
