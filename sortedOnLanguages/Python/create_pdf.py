from reportlab.pdfgen import canvas

def create_pdf():
  pdf = canvas.Canvas("helloworld.pdf")
  pdf.setFont("Helvetica", 12)  # Set font and size
  pdf.drawString(50, 800, "Currently, hello world.")  # Position and write text
  pdf.save()

if __name__ == "__main__":
  create_pdf()

