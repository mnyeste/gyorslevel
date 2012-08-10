#!/usr/bin/python

# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.mime.text import MIMEText

msg = MIMEText('Hello, bello')

# me == the sender's email address
# you == the recipient's email address
msg['Subject'] = 'Test message'
msg['From'] = 'pytonemail@localhost'
msg['To'] = raw_input('Enter mail address: ')

# Send the message via our own SMTP server, but don't include the
# envelope header.
s = smtplib.SMTP('localhost')
s.sendmail(msg['From'], msg['To'], msg.as_string())
s.quit()
