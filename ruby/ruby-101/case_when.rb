cmd = 'add'
case cmd
when 'add'
  puts 'add'
when 'update'
  puts 'update'
else
  puts 'can not understand'
end

# hash way
conds = {
  english: 'Hello',
  chinese: '你好'
}
puts conds[:english] || 'not supported'
