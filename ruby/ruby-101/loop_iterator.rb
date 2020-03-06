# while
counter = 0
while counter < 3
  puts counter
  counter += 1
end

# until
counter = 0
until counter >= 3
  puts counter
  counter += 1
end

# for - inclusive (0 ~ 3)
for counter in 0..3
  puts counter
end

# for - exclusive (0 ~ 2)
for counter in 0...3
  puts counter
end

# each
puts 'each'
(0..3).each do |c|
  puts c
end

# collect
puts 'collect'
l = [1, 2, 3]
l = l.collect { |n| n * 2 } # return modified number
puts l

# infinite, break and next
puts 'infinite, break and next'
i = 10
loop do
  i -= 1
  next if i.odd?

  puts i.to_s
  break if i <= 0
end

# times
3.times { puts '3 times' }

'L'.upto('P') { |c| puts c }
3.upto(5) { |n| puts n }
5.downto(3) { |n| puts n }
