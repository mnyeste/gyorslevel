#!/usr/bin/python

# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.mime.text import MIMEText
from email.MIMEMultipart import MIMEMultipart
from email.MIMEImage import MIMEImage

msg = MIMEMultipart('related')

# me == the sender's email address
# you == the recipient's email address
msg['Subject'] = 'Test message'
msg['From'] = 'pytonemail@localhost'
msg['To'] = raw_input('Enter mail address: ')

# Encapsulate the plain and HTML versions of the message body in an
# 'alternative' part, so message agents can decide which they want to display.
#msgAlternative = MIMEMultipart('alternative')
#msg.attach(msgAlternative)

#msgText = MIMEText('This is the alternative plain text message.')
#msg.attach(msgText)

# We reference the image in the IMG SRC attribute by the ID we give it below
msgText = MIMEText('<b>Some <i>HTML</i> text</b> and an image.<br><img src="cid:image1"><br>Nifty!', 'html')
msg.attach(msgText)

# This example assumes the image is in the current directory
fp = open('plain_logo.png', 'rb')
msgImage = MIMEImage(fp.read())
fp.close()

# Define the image's ID as referenced above
msgImage.add_header('Content-ID', '<image1>')
msg.attach(msgImage)

# Send the message via our own SMTP server, but don't include the
# envelope header.
s = smtplib.SMTP('localhost')
s.sendmail(msg['From'], msg['To'], msg.as_string())
s.quit()
