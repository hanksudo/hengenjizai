x = 'a'
if x == 'a'
  puts 'if cond'
elsif x == 'b'
  puts 'elsif cond'
else
  puts 'nothing happen'
end

puts 'it\'s true!' if true

puts true && false
puts true || false

hungry = false
puts 'eats' if hungry
puts "I'm writing Ruby programs!" unless hungry
