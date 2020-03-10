import setuptools

setuptools.setup(
    name = 'SpotifyPlaylistSearcher',
    packages = ['lyric-grabber'],
    version = '1.0.0',
    description = 'An API wrapper for azlyrics which allows you to programatically extract data',
    author = 'Brian Hu',
    author_email = 'bhurobin221@gmail.com',
    url = 'https://github.com/henry-l-zhou/SpotifyPlaylistSearcher',
    install_requires=['azlyrics'],
    )
