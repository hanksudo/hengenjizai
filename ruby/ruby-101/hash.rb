new_hash = Hash.new
new_hash_2 = {}
puts new_hash == new_hash_2

new_hash["key"] = "value"

puts new_hash

my_hash = {
  'name' => 'Eric',
  'age' => 26,
  'hungry?' => true
}

puts my_hash
puts my_hash['name']
puts my_hash['age']
puts my_hash['hungry?']

my_hash.each { |key, value| puts "#{key} #{value}" }

my_hash.each do |key, value|
  puts "#{key} #{value}"
end

print my_hash.keys
puts
print my_hash.values
puts

# sort by value
hash = {
  'a' => 3,
  'b' => 1
}
hash = hash.sort_by { |_, v| v }
print hash
