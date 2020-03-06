require 'base64'
require 'digest'
require 'openssl'

module AESCrypt
  def self.encrypt(password, iv, cleardata)
    cipher = OpenSSL::Cipher.new('AES-256-CBC')
    cipher.encrypt  # set cipher to be encryption mode
    cipher.key = password
    cipher.iv  = iv

    encrypted = ''
    encrypted << cipher.update(cleardata)
    encrypted << cipher.final
    AESCrypt.b64enc(encrypted)
  end

  def self.decrypt(password, iv, secretdata)
    secretdata = Base64.decode64(secretdata)
    decipher = OpenSSL::Cipher::Cipher.new('aes-256-cbc')
    decipher.decrypt
    decipher.key = password
    decipher.iv = iv unless iv.nil?
    decipher.update(secretdata) + decipher.final
  end

  def self.b64enc(data)
    Base64.encode64(data).gsub(/\n/, '')
  end
end

# password = Digest::SHA256.digest('Nixnogen')
# iv = 'a2xhcgAAAAAAAAAA'

key = Base64.decode64('bBtlxQSu7ZqSIeqP54KwkqE8JWBAlHKXpAVu+YiimQI=')
iv = Base64.decode64('lm8UUrT7FjpBG6XpDdd3Zw==')

# buf = "Who let the dog out"
enc = 'NuIOOW3cKr1wjdH9f6+v0yuQkg1KlaLJ5WvcNvAnClI='
# enc = AESCrypt.encrypt(key, iv, buf)
dec = AESCrypt.decrypt(key, iv, enc)
puts "encrypt length: #{enc.length}"
puts "encrypt in Base64: #{enc}"
puts "decrypt all: #{dec}"
