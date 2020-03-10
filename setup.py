from setuptools import setup, find_packages

setup(
    name='lyric-grabber',
    packages=find_packages(),
    url='https://github.com/henry-l-zhou/SpotifyPlaylistSearcher/lyric-grabber',
    description='Grabs lyrics from a list of songs',
    long_description=open('README.md').read(),
    install_requires=[
        "requests==2.20.0",
        "azapi>=2.1.0",
        ],
    dependency_links = [
     "git+git://github.com/elmoiv/azapi",
    ],
    include_package_data=True,
)
