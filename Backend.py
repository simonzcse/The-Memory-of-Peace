from strgen import StringGenerator as sg
from wordcloud import WordCloud, STOPWORDS, ImageColorGenerator
import time
import matplotlib.pyplot as plt
from profanity_filter import ProfanityFilter #Using spacy

while True:
    text_file = open('localdb.txt', 'r')
    text = text_file.read()
    text_file.close()
    pf = ProfanityFilter()
    pf.censor_char = ' '
    text = pf.censor(text)
    wordcloud = WordCloud(width=2560, height=1080, min_font_size=48, max_font_size=256, max_words=50,
                          background_color="black").generate(text)
    plt.figure()
    plt.imshow(wordcloud, interpolation="bilinear")
    plt.axis("off")
    plt.show()
    wordcloud.to_file("img/" + sg(r"[\w]{30}").render() + ".png")
    text_file = open('localdb.txt', 'w')
    text_file.write(text)
    text_file.close()
    time.sleep(10)
