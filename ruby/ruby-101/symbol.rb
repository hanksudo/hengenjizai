my_symbol = :symbol

symbol_hash = {
  one: 1,
  two: 2
}

puts :sasquatch.to_s
puts 'sasquatch'.to_sym
puts 'sasquatch'.intern


# hash lookup is faster with symbol keys than with string keys.

require 'benchmark'

string_az = Hash[('a'..'z').to_a.zip((1..26).to_a)]
symbol_az = Hash[(:a..:z).to_a.zip((1..26).to_a)]

string_time = Benchmark.realtime do
  100_000.times { string_az["r"] }
end

symbol_time = Benchmark.realtime do
  100_000.times { symbol_az[:r] }
end

puts "String time: #{string_time} seconds."
puts "Symbol time: #{symbol_time} seconds."

# select
movie_ratings = {
  memento: 3,
  primer: 3.5,
  the_matrix: 5,
  truman_show: 4,
  red_dawn: 1.5,
  skyfall: 4,
  alex_cross: 2,
  uhf: 1,
  lion_king: 3.5
}

good_movies = movie_ratings.select { |_, v| v > 3 }
puts good_movies

movie_ratings.each_key { |k| puts k }
movie_ratings.each_value { |v| puts v }

puts %w[1 2 3].map(&:to_i)
puts [1, 2, 3].map(&:to_s)
