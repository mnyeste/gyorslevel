#!/usr/bin/python

# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.mime.text import MIMEText
from email.MIMEMultipart import MIMEMultipart
from email.MIMEImage import MIMEImage

attachment = 'plain_logo.png' 

msg = MIMEMultipart()
msg["To"] = '2cd573bbafcf454ab39c8a4c63a78bdd@localhost'
msg["From"] = 'multipartforpresident'
msg["Subject"] = 'Test message'

fp = open(attachment, 'rb')
img = MIMEImage(fp.read())
fp.close()

msg.attach(img)

email_message = '%s\n%s' % ('Header', 'Body')

s = smtplib.SMTP('localhost')
s.sendmail(msg['From'], msg['To'], email_message)
s.quit()


# emailRezi = smtplib.SMTP(mail_server, mail_server_port)
#emailRezi.set_debuglevel(1)
# emailRezi.login(mail_username, mail_password)
# emailRezi.sendmail(from_addr, to_addr, email_message)
# emailRezi.quit()
