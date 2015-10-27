import base64
from Crypto import Random
from Crypto.Cipher import AES


BS = 16


def pad(s): return s + (BS - len(s) % BS) * chr(BS - len(s) % BS)


def unpad(s): return s[:-ord(s[len(s)-1:])]


class AESCipher:
    def __init__(self, key):
        self.key = key

    def encrypt(self, raw):
        raw = pad(raw)
        iv = Random.new().read(AES.block_size)
        cipher = AES.new(self.key, AES.MODE_CBC, iv)
        return base64.b64encode(iv + cipher.encrypt(raw))

    def decrypt(self, enc):
        enc = base64.b64decode(enc)
        iv = enc[:AES.block_size]
        cipher = AES.new(self.key, AES.MODE_CBC, iv)
        return unpad(cipher.decrypt(enc[AES.block_size:]))


if __name__ == "__main__":
    for i in range(1, 3):
        cipher = AESCipher('fc7deb42e8605cb3a56b429351becc64')
        encrypted = cipher.encrypt(str(i))
        decrypted = cipher.decrypt(encrypted)
        print encrypted
        print decrypted
